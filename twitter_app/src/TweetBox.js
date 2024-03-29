import React, { useState } from "react";
import "./TweetBox.css";
import { Avatar, Button } from "@material-ui/core";
import { collection, addDoc } from "firebase/firestore";

import db from "./firebase";

function TweetBox() {
  const [tweetMessage, setTweetMessage] = useState("");
  const [tweetImage, setTweetImage] = useState("");

  // const sendTweet = (e) => {
  //   e.preventDefault();

  //   db.collection("posts").add({
  //     displayName: "Rafeh Qazi",
  //     username: "cleverqazi",
  //     verified: true,
  //     text: tweetMessage,
  //     image: tweetImage,
  //     avatar:
  //       "https://kajabi-storefronts-production.global.ssl.fastly.net/kajabi-storefronts-production/themes/284832/settings_images/rLlCifhXRJiT0RoN2FjK_Logo_roundbackground_black.png",
  //   });

  //   setTweetMessage("");
  //   setTweetImage("");
  // };
  const sendTweet = (e) => {
    e.preventDefault();

    addDoc(collection(db, "posts"), {
      displayName: "Jojo",
      username: "Jojo He",
      verified: true,
      text: tweetMessage,
      image: tweetImage,
      avatar:
        "https://pbs.twimg.com/profile_images/1554609190848663555/x91MM9qJ_400x400.jpg",
    });

    setTweetMessage("");
    setTweetImage("");
  };

  return (
    <div className="tweetBox">
      <form>
        <div className="tweetBox__input">
          <Avatar src="https://pbs.twimg.com/profile_images/1554609190848663555/x91MM9qJ_400x400.jpg" />
          <input
            onChange={(e) => setTweetMessage(e.target.value)}
            value={tweetMessage}
            placeholder="What's happening?"
            type="text"
          />
        </div>
        <input
          value={tweetImage}
          onChange={(e) => setTweetImage(e.target.value)}
          className="tweetBox__imageInput"
          placeholder="Optional: Enter image URL"
          type="text"
        />

        <Button
          onClick={sendTweet}
          type="submit"
          className="tweetBox__tweetButton"
        >
          Tweet
        </Button>
      </form>
    </div>
  );
}

export default TweetBox;
