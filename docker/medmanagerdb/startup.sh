#!/usr/bin/env bash
echo "executing startup.sh"
nohup sh /home/scripts/init_db.sh &
echo "Starting mysql"
sh entrypoint.sh mysqld