import FirebaseAILogic
import FirebaseCore

@objcMembers
public class FirebaseAILogic: NSObject {

    private let ai: FirebaseAI
    private let model: GenerativeModel

    // https://firebase.google.com/docs/ai-logic/get-started?api=dev#initialize-service-and-model-swift
    public override init() {

// Initialize the Gemini Developer API backend service
        ai = FirebaseAI.firebaseAI(backend: .googleAI())

// Create a `GenerativeModel` instance with a model that supports your use case
        model = ai.generativeModel(modelName: "gemini-2.5-flash")
        super.init()
    }

    public func prompt(_ message: String) async throws -> String {
        let response = try await model.generateContent(message)
        return response.text ?? "No text in response."
    }
}