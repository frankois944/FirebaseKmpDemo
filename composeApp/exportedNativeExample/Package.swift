// swift-tools-version: 5.9
import PackageDescription

let package = Package(
  name: "exportedNativeExample",
  platforms: [.iOS("12.0"), .macOS("10.15"), .tvOS("12.0"), .watchOS("4.0")],
  products: [
    .library(
      name: "exportedNativeExample",
      type: .static,
      targets: ["exportedNativeExample"])
  ],
  dependencies: [
    .package(url: "https://github.com/firebase/firebase-ios-sdk.git", exact: "11.6.0")
  ],
  targets: [
    .target(
      name: "exportedNativeExample",
      dependencies: [
        .product(name: "FirebaseCore", package: "firebase-ios-sdk"),
        .product(name: "FirebaseAnalytics", package: "firebase-ios-sdk"),
        .product(name: "FirebaseAuth", package: "firebase-ios-sdk"),
        .product(name: "FirebaseFirestore", package: "firebase-ios-sdk"),
        .product(name: "FirebaseDatabase", package: "firebase-ios-sdk"),
        .product(name: "FirebaseFunctions", package: "firebase-ios-sdk"),
        .product(name: "FirebaseMessaging", package: "firebase-ios-sdk"),
        .product(name: "FirebaseInstallations", package: "firebase-ios-sdk"),
        .product(name: "FirebaseRemoteConfig", package: "firebase-ios-sdk"),
        .product(name: "FirebasePerformance", package: "firebase-ios-sdk"),
        .product(name: "FirebaseStorage", package: "firebase-ios-sdk"),
      ],
      path: "Sources"

    )

  ]
)
