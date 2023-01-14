# Spotify Token Refresher

A Springboot application to retrive a refreshed token from an access token


## API Reference

#### Get all items


```http
  POST /refresh_token?token=${token}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `token`      | `string` | **Required**. Your Spotify API access token |


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`SPOTIFY_CID` Your Spotify API clientID

`SPOTIFY_CSRT` Your Spotify API clientSecret

The clientID and clientSecret can be found in your Developper Dashboard.



