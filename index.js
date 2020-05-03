import {
    NativeModules,
    Platform
} from "react-native";

export default function setTaskDescription(data) {
    if (Platform.OS === "android") {
        const {
            RNTaskDescription
        } = NativeModules;
        RNTaskDescription.setHeader(data.label || null, data.color || null);
    }
}
