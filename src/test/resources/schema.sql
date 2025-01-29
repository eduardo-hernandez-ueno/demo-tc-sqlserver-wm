-- Crear tabla Users
CREATE TABLE Users
(
    id       INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL,
    password NVARCHAR(50) NOT NULL,
    name     NVARCHAR(50) NOT NULL,
    surname  NVARCHAR(50) NOT NULL
);
