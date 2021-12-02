import React, { useState } from 'react';

import {
  Button,
  SafeAreaView,
  Text,
  View,
} from 'react-native';

const Counter = ({ color = 'black' }) => {
  const [count, setCount] = useState(0);
  console.log(count);
  debugger

  return (
    <View style={{flex: 1, alignItems: "center"}}>
      <Text style={{ fontSize: 40, color }}>{count} <Text style={{ fontWeight: "700"}}>my red text</Text> </Text>
      <Button onPress={() => setCount(count + 1)} title="Increment" />
      <Button onPress={() => setCount(count - 1)} title="Decrement" />
    </View>
  );
};

export default Counter;
