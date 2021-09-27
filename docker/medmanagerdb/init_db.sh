#!/usr/bin/env bash
echo "Executing init db script"

while !(mysqladmin -uroot -proot ping)
do
   sleep 3
   echo "waiting for mysql ..."
done
echo "Mysql is started."
mysql --user="root" --password="root" -e "SET global sql_mode=NO_ENGINE_SUBSTITUTION";
result=`mysql --user="root" --password="root" -e "SELECT COUNT(*) FROM mysql.user WHERE User='allego'"`;
echo $result;
vars=( $result )

if [ "${vars[1]}" -gt 0 ]; then
    echo "User  exists";
else
    echo "User not exists";
    mysql --user="root" --password="root" < /home/scripts/createUser.sql
fi
result=`mysql --user="root"  --password="root" --skip-column-names -e "SHOW DATABASES LIKE 'medmanager'"`

echo $result
if [ "$result" = "medmanager" ]; then
    echo "Database exist"
else
    echo "Creating medmanager database"
    mysql --user="medmanager"  --password="medmanager" -e "create database medmanager";
    echo "medmanager database created successfully."
fi

echo "Import completed"