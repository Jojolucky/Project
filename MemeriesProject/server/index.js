import express from "express";
import mongoose from "mongoose";
import BodyParser from "body-parser";
import Cors from "cors";
import bodyParser from "body-parser";

const app = express();

app.use(bodyParser.json({ limit: "30rmb", extended: true }));
app.use(bodyParser.urlencoded({ limit: "30rmb", extended: true }));
app.use(cors());

// http://www.mongodb.com/cloud/atlas
const CONNECTION_URL =
  "mongodb+srv://memorise:memorise1234@cluster0.aock4mv.mongodb.net/?retryWrites=true&w=majority";
const PORT = process.env.PORT || 5000;
mongoose
  .connect(CONNECTION_URL, { useNewUrlParser: true, useUnifiedtopology: true })
  .then(() =>
    app.listen(PORT, () => console.log("Server running on PORT: ${PORT}"))
  )
  .catch((error) => console.log(error.message));
mongoose.set("useFindAndModify", false);
