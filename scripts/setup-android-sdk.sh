#!/usr/bin/env bash
# Installe le SDK Android (cmdline-tools, platforms;android-34, build-tools;34.0.0)
# dans ${ANDROID_SDK_ROOT:-$HOME/android-sdk}.
set -euo pipefail

SDK_DIR="${ANDROID_SDK_ROOT:-$HOME/android-sdk}"
CMDLINE_VERSION="11076708"
ZIP_URL="https://dl.google.com/android/repository/commandlinetools-linux-${CMDLINE_VERSION}_latest.zip"

mkdir -p "$SDK_DIR/cmdline-tools"
cd "$SDK_DIR/cmdline-tools"

if [ ! -d "latest" ]; then
  echo "Téléchargement command-line tools..."
  curl -fsSL -o cmdline-tools.zip "$ZIP_URL"
  unzip -q cmdline-tools.zip
  mv cmdline-tools latest
  rm cmdline-tools.zip
fi

export ANDROID_SDK_ROOT="$SDK_DIR"
export ANDROID_HOME="$SDK_DIR"
export PATH="$SDK_DIR/cmdline-tools/latest/bin:$SDK_DIR/platform-tools:$PATH"

echo "Acceptation des licences..."
yes | sdkmanager --licenses >/dev/null

echo "Installation des paquets..."
sdkmanager \
  "platforms;android-34" \
  "build-tools;34.0.0" \
  "platform-tools" >/dev/null

echo "✓ SDK Android prêt dans : $SDK_DIR"
echo "Pense à exporter :"
echo "  export ANDROID_SDK_ROOT=$SDK_DIR"
echo "  export ANDROID_HOME=$SDK_DIR"
