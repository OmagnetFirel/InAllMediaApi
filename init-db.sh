#!/bin/bash
set -e

# Função para executar comandos SQL
psql_execute() {
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -c "$1"
}

# Verifica se o usuário já existe
user_exists=$(psql_execute "SELECT 1 FROM pg_roles WHERE rolname='myuser'" | grep -c 1)

if [ "$user_exists" -eq "0" ]; then
    echo "Creating user myuser..."
    psql_execute "CREATE USER myuser WITH PASSWORD 'secret'"
else
    echo "User myuser already exists. Skipping creation."
fi

# Verifica se o banco de dados já existe
db_exists=$(psql_execute "SELECT 1 FROM pg_database WHERE datname='inallmedia'" | grep -c 1)

if [ "$db_exists" -eq "0" ]; then
    echo "Creating database inallmedia..."
    psql_execute "CREATE DATABASE inallmedia"
else
    echo "Database inallmedia already exists. Skipping creation."
fi

# Garante que o usuário tem todos os privilégios no banco de dados
echo "Granting privileges to myuser on inallmedia..."
psql_execute "GRANT ALL PRIVILEGES ON DATABASE inallmedia TO myuser"