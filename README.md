# indiantourguide

Major flow of the app

MainActivity
-------------------------------------------------------------------------
Connection detector check if the device has internet connection active.
If yes, a new async task of downloading data is started
the call goes to JSONParser class with the URL to fetch data
Once the JSONParser returns JSON object, 

JSONParser
-------------------------------------------------------------------------
Makes Http request. Constructs http client, executes the post request and stores results locally.
returns the json object to MainActivity. Parses the object and adds to the list using list adapter.

InfoActivity
-------------------------------------------------------------------------
Holds a tab view to show info about destinations. It has 3 tabs: Hotels, Agents and wiki.

Hotels
-------------------------------------------------------------------------
While browing the list of destinations on MainActivity when a user clicks a destination, a new activity starts.
The activity has 3 tabs hotels, Agents and wiki. The first tab is Hotels.
Once the tab opens, it starts an async task to get hotels data. Uses JSONParser to do this taks.
Publishes data on the UI.

Agents
--------------------------------------------------------------------------
The tab has list of travel agents. The operation is smae as Hotels.

Wiki
--------------------------------------------------------------------------
The tab holds a webview that points to wikipedia page for that destination.


