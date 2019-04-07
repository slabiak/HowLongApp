# HowLong App 
<a  href='https://play.google.com/store/apps/details?id=com.slabiak.tomek.howlongapp'><img  height="45px"  alt='Get it on Google Play'  src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'/></a>
>This is an Android Application to check what is an average waiting time for a table in selected restaurant. It allows users to do two things: add new restaurant to the dabatase or add new time report to the restaurant that is already in the database.
>

![HowLongApp](https://media.giphy.com/media/7zAOljjlEAd5VLyc0o/giphy.gif)



## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/slabiak/HowLongApp.git
```

**2. Set your Google API key for  "Places API"**
+ Open your local gradle.properties file `C:\Users\{user}\.gradle\gradle.properties`
+ Add this line: `GoogleApiKey="PlacesAPIKey"`

**4. Choose release build**

`release` - it uses deployed version of  [HowLong App REST API](https://github.com/slabiak/HowLongAppRestAPI) which is located [here](http://howlongapi.tslabiak.me)
`build` - it uses local version of [HowLong App REST API](https://github.com/slabiak/HowLongAppRestAPI), you need to deploy it locally by yourself

**5. Run the application**

## Built With

* [Retrofit](https://square.github.io/retrofit/)- Used to call REST API's
* [Dagger](https://google.github.io/dagger/android.html) Used for dependency injection
* [Picasso](https://square.github.io/picasso/) Used for loading images from google servers
* [Butterknife](http://jakewharton.github.io/butterknife/) Used for view binding

## Contribute

Let's together make HowLong App awesome!

If you have any suggestions/ideas please feel free to write about it. You are also welcome to fork this project and send pull request with your changes.


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details