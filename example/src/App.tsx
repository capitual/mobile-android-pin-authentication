import React from 'react';
import { TouchableOpacity, View, Text, Alert, StyleSheet } from 'react-native';
import { authenticatePinAndroid } from '@capitual/react-native-android-pin-authentication';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 20,
  },
  button: {
    width: '100%',
    height: 64,
    borderRadius: 8,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'black',
  },
  text: {
    textAlign: 'center',
    fontSize: 24,
    color: 'white',
  },
});

export default function App() {
  const authenticate = async () => {
    try {
      const success = await authenticatePinAndroid();
      if (success) {
        Alert.alert('successful authentication');
      } else {
        Alert.alert('authentication failed');
      }
    } catch (e) {
      Alert.alert('Error authenticating PIN');
    }
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity style={styles.button} onPress={authenticate}>
        <Text style={styles.text}>authenticate</Text>
      </TouchableOpacity>
    </View>
  );
}
