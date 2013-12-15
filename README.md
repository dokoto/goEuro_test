Android Developer - Test
The purpose of this test is to see how you approach a problem and what your solutions look like. The
requirements for this test are simple and should be straightforward to grasp. When implementing a
solution, please keep things simple as well. That said:
Implement an autocompleting travel search form
On the form, the user is required to enter the start and end location and the date of their trip. The start
and end location need to be automatically completed using a list of known locations that can be
requested through a JSON API invocation. When displaying matches, they should be ordered by
distance to the user's current location. Date entry should be facilitated by a date entry widget. A "search"
button should be available when the form has been completely filled out. Tapping the "search" button
should display a "Search is not yet implemented" message to the user.

The app should use one API endpoint:
GET https://api.goeuro.de/api/v1/suggest/position/en/name/STRING
Where STRING is the string that the user has entered so far.
The API endpoint returns JSON documents like these:
{
}
"results" : [ {
"_type" : "Position",
"_id" : 410978,
"name" : "Potsdam, USA",
"type" : "location",
"geo_position" : {
"latitude" : 44.66978,
"longitude" : -74.98131
}
}, {
"_type" : "Position",
"_id" : 377078,
"name" : "Potsdam, Deutschland",
"type" : "location",
"geo_position" : {
"latitude" : 52.39886,
"longitude" : 13.06566
}
} ]
The endpoint always responds with a JSON object that has a results key. The value for that key is either
null or an array of objects. Each object, among other keys, has a name and a geo_positition key. The
geo_position key is an object with latitude and longitude fields.
Your solution
Please implement your solution as an App that we can try out, i.e. send us an .apk file. We need
Android 4.2 + 4.3 compatibility. Also send us the source code to your solution. We use GitHub, so if
you put your source code into a GitHub repository, it will make our life easy.
