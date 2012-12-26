drop sequence COMUNIDADES_seq;
drop sequence MANAGERS_seq;
drop sequence ENCUESTAS_seq;
drop sequence PREGUNTAS_seq;
drop sequence RESPUESTASPOSIBLES_seq;
drop sequence USUARIOS_seq;
drop sequence RESPUESTASUSUARIO_seq;
drop sequence SUSCRIPCIONES_seq;


create sequence COMUNIDADES_seq  start with 1;
create sequence MANAGERS_seq  start with 1;
create sequence ENCUESTAS_seq  start with 1;
create sequence PREGUNTAS_seq  start with 1;
create sequence RESPUESTASPOSIBLES_seq  start with 1;
create sequence USUARIOS_seq  start with 1;
create sequence RESPUESTASUSUARIO_seq  start with 1;
create sequence SUSCRIPCIONES_seq  start with 1;




drop table ENCUESTASUSUARIOS cascade;
drop table RESPUESTASUSUARIO cascade;
drop table PREGUNTAS cascade;
drop table RESPUESTASPOSIBLES cascade;
drop table SUSCRIPCIONES cascade;
drop table USUARIOS cascade;
drop table ENCUESTAS cascade;
drop table MANAGERS cascade;
drop table COMUNIDADES cascade;

CREATE TABLE MANAGERS(
       idManager INTEGER NOT NULL PRIMARY KEY
     , nombre VARCHAR(100) 
);


CREATE TABLE COMUNIDADES (
       idComunidad INTEGER NOT NULL PRIMARY KEY
     , nombre VARCHAR(100) NOT NULL
     , twitter VARCHAR(100)
     , alcance VARCHAR(100)
     , descripcion VARCHAR(1000)
     , tipo VARCHAR(5)
     , radio INTEGER
     , longitud REAL
	 , latitud REAL
	 , idManager INTEGER
	 , CONSTRAINT FK_managerComunidad FOREIGN KEY (idManager)
                  REFERENCES MANAGERS (idManager)
);

CREATE TABLE ENCUESTAS(
     idEncuesta INTEGER NOT NULL PRIMARY KEY
    , fechaLimite DATE NOT NULL
	, idComunidad INTEGER 
     , CONSTRAINT FK_encuestaComunidad FOREIGN KEY (idComunidad)
                  REFERENCES COMUNIDADES (idComunidad)
);

CREATE TABLE USUARIOS(
      idUsuario INTEGER NOT NULL PRIMARY KEY
    , email   VARCHAR(100)
	, idRegistration varchar(500)
	, telefono   VARCHAR(20)
	, nombre   VARCHAR(100)
	, publicidad   VARCHAR(1)
);  

ALTER TABLE usuarios ALTER COLUMN idregistration varchar(500);
ALTER TABLE usuarios ADD CONSTRAINT usuariosEmail_uk UNIQUE (email);

CREATE TABLE ENCUESTASUSUARIOS(
     idEncuesta INTEGER NOT NULL 
    , idUsuario INTEGER NOT NULL
    , CONSTRAINT PK_ENCUESTASUSUARIOS PRIMARY KEY (idEncuesta,idUsuario)
    , CONSTRAINT FK_encuUsuEncuesta FOREIGN KEY (idEncuesta)
                  REFERENCES ENCUESTAS(idEncuesta)   
    , CONSTRAINT FK_encuUsuUSuarios FOREIGN KEY (idUsuario)
                  REFERENCES USUARIOS(idUsuario)                   
);

CREATE TABLE PREGUNTAS(
      idPregunta INTEGER NOT NULL PRIMARY KEY
    , texto      VARCHAR(500)
    , idEncuesta INTEGER
	, idComunidad INTEGER
    , CONSTRAINT FK_preguntaEncuesta FOREIGN KEY (idEncuesta)
                  REFERENCES ENCUESTAS(idEncuesta)
    , CONSTRAINT FK_preguntaComunidad FOREIGN KEY (idComunidad)
                  REFERENCES COMUNIDADES(idComunidad)				  
);


CREATE TABLE RESPUESTASPOSIBLES(
      idRespuestaPosible INTEGER NOT NULL PRIMARY KEY
    , idPregunta     INTEGER NOT NULL 
    , valor      VARCHAR(100)
    , CONSTRAINT FK_resPosPregunta FOREIGN KEY (idPregunta)
                  REFERENCES PREGUNTAS(idPregunta)
);


CREATE TABLE RESPUESTASUSUARIO(
      idRespuestaUsuario INTEGER NOT NULL PRIMARY KEY
    , idUsuario          INTEGER NOT NULL 
    , idRespuestaPosible INTEGER NOT NULL
    , CONSTRAINT FK_resUsuPregunta FOREIGN KEY (idRespuestaPosible)
                  REFERENCES RESPUESTASPOSIBLES(idRespuestaPosible)
    , CONSTRAINT FK_resUsuUsuario FOREIGN KEY (idUsuario)
                  REFERENCES USUARIOS(idUsuario)				  
);
  

CREATE TABLE SUSCRIPCIONES (
       idSuscripcion INTEGER NOT NULL PRIMARY KEY
     , fechaAlta DATE
     , idUsuario INTEGER
	 , idComunidad  INTEGER
     , CONSTRAINT FK_SuscripcionUsuario FOREIGN KEY (idUsuario)
                  REFERENCES USUARIOS(idUsuario)	
     , CONSTRAINT FK_SuscripcionComunidad FOREIGN KEY (idComunidad)
                  REFERENCES COMUNIDADES(idComunidad)					  
);

