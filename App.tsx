import {Button, StyleSheet, Text, TextInput, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import CryptModule from './src/native_modules/CryptModule';
import NativeNavigationModule from './src/native_modules/NativeNavigationModule';

type Props = {};

const App = ({}: Props) => {
  const [text, setText] = useState('');
  const [encryptedText, setEncryptedText] = useState('');

  useEffect(() => {
    const encryptText = async () => {
      const newEncryptedText = await CryptModule.encryptTextPromise(text);
      setEncryptedText(newEncryptedText);
    };

    text.length && encryptText();
  }, [text]);

  const showNativeLoginScreen = async () => {
    try {
      const result = await NativeNavigationModule.showLoginActivity();
      console.log('result: ', result);
    } catch (error) {
      console.log('error: ', error);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Encriptar</Text>
      <TextInput
        style={styles.input}
        value={text}
        onChangeText={setText}
        placeholder="Texto a encriptar"
      />
      <Text style={styles.title}>{encryptedText}</Text>

      <Button
        title="Show native login screen"
        onPress={showNativeLoginScreen}
      />
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {padding: 20, flex: 1},
  title: {fontSize: 20, fontWeight: 'bold', color: 'black'},
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    borderRadius: 10,
    marginTop: 10,
    padding: 5,
  },
});
