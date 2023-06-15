import React, { useState, useEffect } from "react";
import { View, Text, FlatList, StyleSheet, Image } from "react-native";
import axios from "axios";

const API_URL = "https://myselena.org/wp-json/learnpress/v1/courses";

const CourseList = () => {
  const [course, serCourse] = useState([]);

  useEffect(() => {
    axios
      .get(API_URL)
      .then((response) => {
        serCourse(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const renderItem = ({ item }) => (
    <View style={styles.item}>
      <Text style={styles.title}>{item.name}</Text>
      <Text style={styles.details}>ID: {item.id}</Text>
      <Text style={styles.details}>Slug: {item.slug}</Text>
      <Text style={styles.details}>Date: {item.date_created}</Text>
      <Image source={{ uri: item.image }} style={styles.image} />
    </View>
  );

  return (
    <View style={styles.container}>
      <Text style={styles.header}></Text>
      <FlatList
        data={course}
        renderItem={renderItem}
        keyExtractor={(item) => item.id}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#917FB3",
  },
  header: {
    fontSize: 24,
    fontWeight: "bold",
    marginBottom: 20,
  },
  item: {
    backgroundColor: "#FDE2F3",
    padding: 18,
    marginVertical: 8,
    marginHorizontal: 16,
  },
  title: {
    fontSize: 20,
    fontWeight: "bold",
  },
  details: {
    fontSize: 17,
    marginTop: 5,
  },
  image: {
    width: "100%",
    aspectRatio: 1,
    resizeMode: "contain",
  },
});

export default CourseList;
