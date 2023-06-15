import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import CourseList from "./screens/CourseList";

const Stack = createStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Course List" component={CourseList} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
