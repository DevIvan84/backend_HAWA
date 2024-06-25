IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'hawadb')
BEGIN
    CREATE DATABASE hawadb;
END