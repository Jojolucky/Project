// import firebase from "firebase/app";
import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";

// import "firebase/firestore";
// // // import firebase from "firebase/app";

// import firebase from "firebase/app";
// import "firebase/firestore";
// import { initializeApp } from "firebase/app";

const firebaseConfig = {
  apiKey: "AIzaSyDsBF1rS48Xcr94N-jWsdqdmYch55pzM6w",
  authDomain: "twitter-97879.firebaseapp.com",
  databaseURL: "https://twitter-97879-default-rtdb.firebaseio.com",
  projectId: "twitter-97879",
  storageBucket: "twitter-97879.appspot.com",
  messagingSenderId: "1046777924896",
  appId: "1:1046777924896:web:afce607aa3d5f028031b45",
  measurementId: "G-2VTZFXY6L2",
};

// const firebaseApp = firebase.initializeApp(firebaseConfig);

// const db = firebaseApp.firestore();

// export default db;
const firebaseApp = initializeApp(firebaseConfig);
const db = getFirestore(firebaseApp);
// const db = firestore(firebaseApp);

export default db;
