import Foundation
import FirebaseCore
import FirebaseAnalytics
import FirebasePerformance

@objcMembers
public class FirebaseSdk: NSObject {

    @objc
    public static func configure() {
        FirebaseApp.configure()
    }
}