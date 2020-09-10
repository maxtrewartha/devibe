# devibe
A simple youtube upload to discord webhook notifier, easy to setup (sorta)
> Don't @ me if my code is shit, make a pull request instead :)

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
port: 58410
useEveryone: false
```
There are currently no config for subscribed topics (they haven't been implmenented yet

### Commands

`stop` -> Stops the program  
`sub [channelId]` -> Subscribes to a channel for the default amount of time  
`url` -> Swaps the discord webhook url  
`port` -> Swaps the port of the server  
`reload` -> reloads the server  
`save` -> Saves the config  
`config` -> Prints out the current config  


## The Future
### Known Issues
### To Do
 - Resend subscribe events when the amount of seconds is over
 - Send unsubscribe requests from the app
 - Editable post notifications
 

## Other sources
Sources:
XML: [https://argonrain.wordpress.com/2009/10/27/000/](https://argonrain.wordpress.com/2009/10/27/000/)  
Discord Webhook [https://gist.github.com/k3kdude/fba6f6b37594eae3d6f9475330733bdb](https://gist.github.com/k3kdude/fba6f6b37594eae3d6f9475330733bdb)
