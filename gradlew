#!/usr/bin/env bash
set -euo pipefail

script_dir="$(
  cd "$(dirname "${BASH_SOURCE[0]}")"
  pwd
)"
properties_file="$script_dir/gradle/wrapper/gradle-wrapper.properties"

if [[ ! -f "$properties_file" ]]; then
  echo "Missing Gradle wrapper properties at $properties_file" >&2
  exit 1
fi

distribution_url="$(awk -F= '/^distributionUrl=/{print $2}' "$properties_file")"
distribution_url="${distribution_url//\\:/:}"

if [[ -z "$distribution_url" ]]; then
  echo "Unable to resolve distributionUrl from $properties_file" >&2
  exit 1
fi

distribution_zip="$(basename "$distribution_url")"
distribution_name="${distribution_zip%.zip}"
gradle_dir_name="${distribution_name%-bin}"
cache_root="${GRADLE_USER_HOME:-$HOME/.gradle}/wrapper/dists/$distribution_name"
install_dir="$cache_root/$gradle_dir_name"
gradle_bin="$install_dir/bin/gradle"

download() {
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$distribution_url" -o "$1"
    return
  fi

  if command -v wget >/dev/null 2>&1; then
    wget -qO "$1" "$distribution_url"
    return
  fi

  echo "Either curl or wget is required to download $distribution_url" >&2
  exit 1
}

if [[ ! -x "$gradle_bin" ]]; then
  mkdir -p "$cache_root"
  temp_dir="$(mktemp -d)"
  trap 'rm -rf "$temp_dir"' EXIT
  archive_path="$temp_dir/$distribution_zip"

  download "$archive_path"
  unzip -q "$archive_path" -d "$temp_dir"
  rm -rf "$install_dir"
  mv "$temp_dir/$gradle_dir_name" "$install_dir"
fi

exec "$gradle_bin" "$@"
