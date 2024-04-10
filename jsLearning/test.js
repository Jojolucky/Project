// console.log(2 + 2);
// var i = 7;
// console.log(i);
// console.log("hello wordl");
// console.log(3 >= 2);
// console.log(100 == "100"); // check only the value
// console.log(100 === "100"); // check the value and the type
// console.log(1 != "1");
// console.log(1 !== "1");

// console.log("Start learning function");
// function addnumber(a, b) {
//   var c = a + b;
//   console.log(c);
// }

// addnumber(1, 1);
// addnumber(1, 3);

// try {
//   console.log(a);
// } catch (error) {
//   console.log(error);
//   console.log("There is an error");
// }

// try {
//   throw new Error();
//   console.log("Hello");
// } catch (err) {
//   console.log("Goodbye");
// }

// var str = "Hello";
// str.match("jello");

// try {
//   console.log(a);
// } catch (e) {
//   console.log("caught");
// }

// console.log("Hello");
// console.log();

// var virtualPet = {
//   sleepy: true,
//   nap: function () {
//     this.sleepy = false;
//   },
// };
// console.log(virtualPet.sleepy); // true
// virtualPet.nap();
// console.log(virtualPet.sleepy);

// if (true) {
//   var varVariable = "I'm using var";
//   let letVariable = "I'm using let";
//   const constVariable = "I'm using const";
//   var varVariable = "godd";
// }
// console.log(varVariable); // 正常输出 "I'm using var"
// // console.log(letVariable); // 报错：letVariable is not defined
// // console.log(constVariable); // 报错：constVariable is not defined

// var varVariable = "new value"; // 允许
// let letVariable = "new value"; // 允许
// // constVariable = "new value"; // 报错：Assignment to constant variable.

// function meal(animal) {
//   animal.food = animal.food + 10;
// }

// var dog = {
//   food: 10,
// };
// meal(dog);
// meal(dog);

// console.log(dog.food);

// let { PI } = Math.PI;
// console.log(PI === Math.PI);

// const car = {
//   engine: true,
//   steering: true,
//   speed: "slow",
// };
// const scar = Object.create(car);
// scar.speed = "fast";
// console.log("The scar object:", scar);

// console.log("____________________");
// // 可以打印出所有的属性，包括从原型继承的属性
// for (prop in scar) {
//   console.log(prop);
// }
// console.log("____________________");
// // 只能打印自己的属性，继承的属性无法打印
// for (prop of Object.keys(scar)) {
//   console.log(prop + ":" + scar[prop]);
// }

// var greet = "Hello";
// var place = "World";
// console.log(greet + " " + place + "!");
// console.log(`${1 + 1 + 1 + 1 + 1} stars!`);

// const meal = ["soup", "steak", "ice cream"];
// let [starter] = meal;
// console.log(starter);

// var h1 = document.createElement("h1");
// h1.innerText = "Type into the input to make this text change";

// var input = document.createElement("input");
// input.setAttribute("type", "text");

// document.body.innerText = "";
// document.body.appendChild(h1);
// document.body.appendChild(input);

// input.addEventListener("change", function () {
//   h1.innerText = input.value;
// });

// var h1 = document.querySelector("h1");
// var arr = ["Example Domain", "First Click", "Second Click", "Third Click"];
// function handleClicks() {
//   switch (h1.innerText) {
//     case arr[0]:
//       h1.innerText = arr[1];
//       break;
//     case arr[1]:
//       h1.innerText = arr[2];
//       break;
//     case arr[2]:
//       h1.innerText = arr[3];
//       break;
//     default:
//       h1.innerText = arr[0];
//   }
// }
// h1.addEventListener("click", handleClicks);
// const letter = "a";
// letter = "b";

// const a = true;
// if (!a) {
//   console.log("Green");
// } else {
//   console.log("Blue");
// }
// var result;
// console.log(result);
// result = 7;

const x = 2;
let y = 4;
function update(arg) {
  return Math.random() + y * arg;
}
y = 2; // This is the statement you need
const result = update(x);
console.log(result);
