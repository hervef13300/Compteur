# Compteur — Provence Cloud

Application Android simple de comptage manuel (style « clicker » de cinéma) habillée à la charte graphique **Provence Cloud**.

- Bouton **+** pour incrémenter (0 → 9999).
- Bouton **−** pour corriger.
- Menu ⋮ → **Réinitialiser** ou **À propos**.
- Le compteur est sauvegardé entre les sessions.

## Télécharger l'APK

L'APK est construit automatiquement par GitHub Actions à chaque push :

- **Direct dans le dépôt** : [`release/compteur-debug.apk`](release/compteur-debug.apk) — la CI commit l'APK fraîchement compilé sur la branche après chaque push.
- **Artefact CI** : onglet **Actions** → workflow *Build APK* → artefact *compteur-debug-apk*.
- **Releases GitHub** : créer une release attache automatiquement l'APK.

> ⏱ Au tout premier push, attendre ~3 min que la CI compile et pousse `release/compteur-debug.apk` ; rafraîchir la page.

## Installation

1. Activer **Sources inconnues** dans les paramètres Android.
2. Transférer le `.apk` sur le téléphone (USB, Drive, etc.).
3. L'ouvrir avec un gestionnaire de fichiers et confirmer l'installation.

## Build local

Prérequis : JDK 17.

```bash
# 1. Installer le SDK Android (une seule fois)
./scripts/setup-android-sdk.sh
export ANDROID_SDK_ROOT=$HOME/android-sdk
export ANDROID_HOME=$HOME/android-sdk

# 2. Compiler
./gradlew assembleDebug

# APK généré : app/build/outputs/apk/debug/app-debug.apk
```

## Stack

- Kotlin · AGP 8.5 · Gradle 8.7
- AndroidX, Material 3
- minSdk 24 · targetSdk 34
