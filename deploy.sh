# heroku plugins:install heroku-cli-deploy

heroku war:deploy stravafriends.war --app stravafriends
heroku ps:scale web=1 --app stravafriends

