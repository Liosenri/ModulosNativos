import {NativeModules} from 'react-native';
const {NativeNavigationModule} = NativeModules;

interface LoginResul {
  email: string;
  password: string;
}

interface NativeNavigationModuleType {
  showLoginActivity(): Promise<LoginResul>;
}

export default NativeNavigationModule as NativeNavigationModuleType;
