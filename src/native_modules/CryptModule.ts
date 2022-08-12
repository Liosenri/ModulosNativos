import {NativeModules} from 'react-native';
const CryptModule = NativeModules.CryptModule;

interface CryptModuleType {
  encryptText(
    text: string,
    onFailure: (error: Error) => void,
    onSuccess: (encryptedText: string) => void,
  ): void;
  encryptTextPromise(text: string): Promise<string>;
  decryptText(
    text: string,
    onFailure: (error: Error) => void,
    onSuccess: (decryptedText: string) => void,
  ): void;
  decriptTextPromise(text: string): Promise<string>;
}

export default CryptModule as CryptModuleType;
