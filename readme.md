# Reddit Comment Bot
This allows you to scan for comments in a subreddit and reply to them with your own unique flavour
  - Pick a subreddit to scan
  - Designate a specific type of comment to search for
  - Set your bot's reply

### Requirements
  - [Python 3](https://www.python.org/downloads/)
  - [Praw](https://praw.readthedocs.io/en/latest/getting_started/installation.html)
  - A Reddit Account (Obviously)

### Setup
###### Reddit App:
1. [Navigate to the Apps page ](https://www.reddit.com/prefs/apps/)
2. Click *create an app*
3. **name:** Set a name for your app
4. **type:** Script
5. **description:** Optional
6. **about url:** Optional
7. **redirect uri:** http://localhost:8080
8. Note the outputted *client id* and *secret*

###### config.py:
1. **username:** your Reddit username
2. **password:** your Reddit password
3. **client_id:** the outputted client id
4. **client_secret:** the outputted secret

###### reddit_bot.py:

Set the subreddit to search (default = "r/dankruto"):
```python
r.subreddit('dankruto') 
```
Comment search criteria (default = "dattebayo"):
```python
if "sample user comment"
```
Bot's comment reply (default = "Dattebayo!"):
```python
comment.reply("Hey, I like your comment!")
```

### Usage

Navigate into the bot directory.
Run your bot:
```
python reddit_bot.py
```