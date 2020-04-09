import praw
import config
import time
import os

def bot_login():
	#print("Logging in...")
	r = praw.Reddit(username = config.username,
				password = config.password,
				client_id = config.client_id,
				client_secret = config.client_secret,
				user_agent = "NarutoLove (by /u/Uni_Omni)")
	#print("Logged in!")

	return r

def run_bot(r, comments_replied_to):
	#print("Searching last 100 comments")
	subreddit = r.subreddit("dankruto")
	while True:
		for submission in subreddit.new(limit=100):
			print(submission.title)
			submission.comments.replace_more(limit=None)
			comment_queue = submission.comments[:]  # Seed with top-level
			while comment_queue:
				comment = comment_queue.pop(0)
				if comment.score >= 1000:
					comment.reply("Dattebayo! You get a Chou Senpo Odama Rasengan")
				elif comment.score >= 500:
					comment.reply("Dattebayo! You get a Futon:Rasenshuriken")
				elif comment.score >= 100:
					comment.reply("Dattebayo! You mastered sage mode")
				if "dattebayo" in comment.body.lower() and comment.id not in comments_replied_to and comment.author != r.user.me():
					#print("Here!")
					comment.reply("Dattebayo!")
					print(comment.body)
					#print("Replied to comment " + comment.id + "," + comment.karma)
					comments_replied_to.append(comment.id)
					with open ("comments_replied_to.txt", "a") as f:
						f.write(comment.id + "\n")
					#print(len(comments_replied_to))
					if len(comments_replied_to) >= 1000:
						print(comments_replied_to)
				comment_queue.extend(comment.replies)
		time.sleep(20)

def get_saved_comments():
	if not os.path.isfile("comments_replied_to.txt"):
		comments_replied_to = []
	else:
		with open("comments_replied_to.txt", "r") as f:
			comments_replied_to = f.read()
			comments_replied_to = comments_replied_to.split("\n")

	return comments_replied_to

r = bot_login()
comments_replied_to = get_saved_comments()
print(comments_replied_to)

while True:
	run_bot(r, comments_replied_to)