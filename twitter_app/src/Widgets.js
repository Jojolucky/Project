import React from "react";
import "./Widgets.css";
import {
  TwitterTimelineEmbed,
  TwitterShareButton,
  TwitterTweetEmbed,
} from "react-twitter-embed";
import SearchIcon from "@material-ui/icons/Search";
import { withWidth } from "@material-ui/core";
// import db from "./firebase";

function Widgets() {
  return (
    <div className="widgets">
      <div className="widgets__input">
        <SearchIcon className="widgets__searchIcon" />
        <input placeholder="Search Twitter" type="text" />
      </div>

      <div className="widgets__widgetContainer">
        <h2>What's happening</h2>
        {/* <TwitterTweetEmbed tweetId={"858551177860055040"} /> */}
        {/* <TwitterTweetEmbed
          tweetId={"1554609164403593217"}
          consumerKey={"MCw0p95G52gLEK443BTNvnbEa"}
          consumerSecret={"6Nolr4mPRfav5T1CXM2XBC98QdjTAowDQTr74OI4C48nAPlEbB"}
          accessToken={"1554609164403593217-Q5QpC5HPQDmIDFFzlSxJdNCztte63J"}
          accessTokenSecret={"bRjNDNVj6FM0K5VuKm1ZvGM7K99lMeo35xdW0JhF9kpjb"}
        /> */}
        <TwitterTimelineEmbed
          //858551177860055040
          sourceType="profile"
          screenName="Dian0214Dian" // Dian0214Dian
          options={{ height: 400, width: 300 }}
        />
        <TwitterTimelineEmbed
          //858551177860055040
          sourceType="profile"
          screenName="Tesla" // Dian0214Dian
          options={{ height: 400 }}
        />
        {/* <TwitterShareButton
          url={"https://www.facebook.com/SHEINOFFICIAL"}
          options={{ text: "#reactjs is awesome", via: "Jojo" }}
        /> */}
      </div>
    </div>
  );
}
export default Widgets;
