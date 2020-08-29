# DeVibe
A simple youtube upload to discord webhook notifier, easy to setup (sorta)
> Don't @ me if the code is shit, make a pull request instead :)

## Setup
### Server side
 - Make a new folder
 - Put the devibe jar in the folder and run it once
 - Change the config to your pleasure (if you change the port make sure the port is open)
 - Re-run the jar
### For you
 - Nagivate to [https://pubsubhubbub.appspot.com/subscribe](https://pubsubhubbub.appspot.com/subscribe)
 - To make a new subscribe request set the:
   - Callback to http://[yourip]:[port]
   - Topic URL to https://www.youtube.com/xml/feeds/videos.xml?channel_id=[channelId]
   - Verify type to Synchronous
   - Mode to subscribe
 - Hit the `Do it!` button and a message will appear on your command line saying:  
   `GET REQUEST from xxx.xxx.xxx.xxx with challenge: xxxxxxxxxxxxxxxxxxxx`
 - Now wait for your favourite youtubers to upload!
 
## Usage
There is current no other commands apart from `stop` which exist the application without needing Ctrl + C

### config.yaml
All config is loaded from `config.yaml`, here is an example of what your config file looks like:
```yaml
webhook: https://discordapp.com/api/webhooks/XXXXXXXXXXXXXXXXXX
port: 7000
```
There are currently no config for subscribed topics (they haven't been implmenented yet

## The Future
### Known Issues
 - When youtuber deletes a video it crashes
 - Server receives two post requests and posts to the webhook twice
### To Do
 - Send subscribe requests from the app
 - Send unsubscribe requests from the app
 - Hot swapping the server port
 - Hot swapping discord webhook url
 - Editable post notifications
