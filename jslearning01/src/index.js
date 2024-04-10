import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";

const root = ReactDOM.createRoot(document.getElementById("root"));
// 函数的两种定义方法
// const ref = () => {
//   root.render(<App />);
// };
function ref() {
  root.render(<App />);
}
setInterval(ref, 1000); // 每1000毫秒更新
