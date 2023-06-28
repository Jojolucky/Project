import express from "express";
import mongoose from "mongoose";
import bodyParser from "body-parser";
import cors from "cors";

import postRoutes from "./routes/posts.js";

const app = express();

app.use("/posts", postRoutes);
app.use(bodyParser.json({ limit: "30mb", extended: true }));
app.use(bodyParser.urlencoded({ limit: "30mb", extended: true }));
app.use(cors());

// set up database
// http://www.mongodb.com/cloud/atlas
//Mongoose是一个用于在Node.js环境中与MongoDB数据库进行交互的对象建模工具
const CONNECTION_URL =
  "mongodb+srv://memorise:memorise1234@cluster0.aock4mv.mongodb.net/?retryWrites=true&w=majority";
const PORT = process.env.PORT || 3000;

mongoose
  .connect(CONNECTION_URL, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => {
    app.listen(PORT, () => console.log(`Server running on PORT: ${PORT}`));
  })
  .catch((error) => console.log(error.message));

// mongoose.set("useFindAndModify", false);
