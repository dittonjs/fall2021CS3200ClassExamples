import React, { useState } from 'react';

import {
  Button,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import Counter from './src/Counter';

const App = () => {

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <Counter color="blue"/>
      <Counter />
      <Counter color="#FFFF00"/>
    </SafeAreaView>
  );
};

export default App;
