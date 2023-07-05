# @capitual/react-native-android-pin-authentication

### Android authentication pin adapter to React Native.
#### **only works on android**
<hr />

## Introduction

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

<hr/>

## Installation

```sh
npm install @capitual/react-native-android-pin-authentication
```

<hr/>

## Usage

```tsx
import React from 'react';
import { TouchableOpacity, View, Text, Alert, StyleSheet } from 'react-native';
import {
  authenticatePinAndroid,
  isAvailablePin,
} from '@capitual/react-native-android-pin-authentication';

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
    marginVertical: 10,
  },
  text: {
    textAlign: 'center',
    fontSize: 24,
    color: 'white',
  },
});

export default function App() {
  const handleIsAvailable = async () => {
    const success = await isAvailablePin();
    if (success) {
      Alert.alert('Pin is available');
    } else {
      Alert.alert('Pin is not available');
    }
  };

  const handleAuthenticate = async () => {
    try {
      const success = await authenticatePinAndroid();

      if (success) {
        Alert.alert('Successful Authentication');
      } else {
        Alert.alert('Authentication Failed');
      }
    } catch (e) {
      Alert.alert('Error Authenticating PIN');
    }
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity style={styles.button} onPress={handleIsAvailable}>
        <Text style={styles.text}>Is available? </Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.button} onPress={handleAuthenticate}>
        <Text style={styles.text}>Android Authentication</Text>
      </TouchableOpacity>
    </View>
  );
}

```

<hr/>

## Contributing

See the [contributing guide](./CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

<hr/>

## License

[MIT License](./LICENSE). 🙂

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob). 😊
