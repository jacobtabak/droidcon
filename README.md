Droidcon NYC 2014 Retrofit Demo
===============================

This sample project demonstrates Retrofit implementations for the Foursquare and Reddit APIs.

Foursquare
----------

The Foursquare demo is a rather straightforward retrofit implementation.  The documentation for the 
venues endpoint can be found 
[here](https://developer.foursquare.com/docs/venues/search).

You can find the Foursquare models [here] 
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/foursquare/model), and the Retrofit
service interface in [FoursquareService.java]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/foursquare/FoursquareService.java).

These models implement a strict pattern of defining the JSON field names in constants at the top
of the file because our [legacy async task implementation]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/foursquare/legacy/VenueSearchTask.java)
does not benefit from the @SerializedName annotation, an in order to maintain refactorability,
the JSON field names must be defined separately.  

The 4sq service interface also has a nested class called `FoursquareService.Implementation` which 
can be used to access the default implementation of the service like this: 

```java
FoursquareService.Implementation.get()
```

With an instance of the service, you may call any methods defined in the FoursquareService 
interface, like so: 

```java
FoursquareService.Implementation.get().searchVenues("New York");
```

To view the API in action, either look at the [tests]
(app/src/androidTest/java/com/timehop/droidcon2014retrofitsample/data/foursquare/FoursquareTests.java) 
or [sample activity]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/VenueSearchActivity.java).
 
Reddit
------
 
The reddit demo is a bit more complex since reddit returns dynamically-typed JSON.  

The API documentation for reddit is available [here](http://www.reddit.com/dev/api).  The important
thing to take out of this is that reddit 'things' are typed as follows:

* t1:	Comment
* t2:	Account
* t3:	Link
* t4:	Message
* t5:	Subreddit
* t6:	Award
* t8:	PromoCampaign

The reddit models in this sample can be found [here]
(/app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/model) and the Retrofit API 
interface can be found [here]
(/app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/RedditService.java).

The challenge is to instruct GSON through the use of a type adapter how to instantiate the correct 
subclass of `RedditObject` at runtime.   

This is achieved through the [RedditObjectDeserializer]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/RedditObjectDeserializer.java)
with the help of the [RedditType enumeration]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/RedditType.java). 

The [RedditType enum] 
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/RedditType.java) maps the 
types defined in reddit documentation to the Java classes that model them.

The [RedditObjectDeserializer]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/RedditObjectDeserializer.java)
first examines the wrapped JSON objects that look like this:

```json
{
  "kind": "t1",
  "data": { ... }
}
```

and convert them into a [RedditObjectWrapper]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/model/RedditObjectWrapper.java)
that looks like this:

```java
public class RedditObjectWrapper {
  RedditType kind;
  JsonElement data;
}
```

Then, [RedditObjectDeserializer]
(app/src/main/java/com/timehop/droidcon2014retrofitsample/data/reddit/RedditObjectDeserializer.java)
passes the wrapped json correct derived class into the existing deserialization context to create
the correct model.

```java
RedditObjectWrapper wrapper = new Gson().fromJson(json, RedditObjectWrapper.class);
      return context.deserialize(wrapper.data, wrapper.kind.getDerivedClass());
```

Persisting Cookies
------------------
Check out [this gist](http://git.io/U2rqkg) for an easy way to persist cookies.