# 🐾 FindAnimal

<p align="center">
  <strong>Aplikasi katalog hewan berbasis Jetpack Compose dengan pencarian, detail, tambah data, dan favorite lokal.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-Android-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack-Compose-4285F4?logo=jetpackcompose&logoColor=white" alt="Compose"/>
  <img src="https://img.shields.io/badge/UI-Material%203-6750A4" alt="Material 3"/>
  <img src="https://img.shields.io/badge/Database-Room-orange" alt="Room"/>
  <img src="https://img.shields.io/badge/Image-Coil-blue" alt="Coil"/>
</p>

---

## 📖 Tentang Project

**FindAnimal** adalah aplikasi Android yang menampilkan katalog hewan beserta gambar, nama ilmiah, asal, kategori, dan deskripsi. Project ini dibangun dengan Jetpack Compose dan menyimpan data favorite menggunakan Room Database.

Data utama aplikasi masih berupa data lokal, sehingga cocok untuk latihan Compose, navigasi, state management sederhana, dan local persistence.

## ✨ Fitur

| Fitur | Deskripsi |
|-------|-----------|
| 🐅 **Katalog Hewan** | Menampilkan daftar hewan dari data lokal |
| 🔍 **Pencarian** | Mencari hewan berdasarkan nama |
| 📄 **Detail Hewan** | Menampilkan detail lengkap hewan |
| ➕ **Tambah Hewan** | Menambahkan data hewan baru ke list aplikasi |
| ⭐ **Favorite** | Menyimpan hewan favorit ke database lokal |
| 🧭 **Navigation Compose** | Navigasi antar screen menggunakan Compose Navigation |

## 🛠️ Tech Stack

| Layer | Teknologi |
|-------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation Compose |
| Image Loader | Coil Compose |
| Database | Room |
| State | ViewModel dan UiState |

## 📂 Struktur Project

```text
FindAnimal/
├── app/src/main/java/com/dicoding/findanimal/
│   ├── data/                  # Model, dummy data, dan repository
│   ├── di/                    # Dependency injection sederhana
│   ├── local/                 # DAO dan Room Database
│   ├── ui/
│   │   ├── components/        # Komponen Compose reusable
│   │   ├── navigation/        # Route dan screen definition
│   │   └── screen/            # Home, Detail, Add, Favorite, About
│   ├── FindAnimal.kt          # Root composable aplikasi
│   └── MainActivity.kt
└── app/build.gradle.kts
```

## 🚀 Cara Menjalankan

1. Buka project di **Android Studio**.
2. Pastikan menggunakan **JDK 17**.
3. Jalankan **Gradle Sync**.
4. Run aplikasi pada emulator atau device Android.

## 🧠 Alur Data

```text
AnimalData -> Repository -> ViewModel -> Compose Screen
                              ↓
                         Room Database
                         untuk Favorite
```

## 📄 Catatan

Project ini dibuat sebagai latihan membangun aplikasi Android modern dengan Jetpack Compose, Room, dan Navigation Compose.