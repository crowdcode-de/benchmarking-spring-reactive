CREATE TABLE ALBUM (
  ALB_DISC_ID VARCHAR(128),
  ALB_ARTIST  VARCHAR(512),
  ALB_GENRE   VARCHAR(128),
  ALB_NAME    VARCHAR(512),
  ALB_HASH    VARCHAR(2048),
  ALB_YEAR    INTEGER
);

CREATE TABLE TRACK (
  TRK_ALB_ID  VARCHAR(128),
  TRK_NAME    VARCHAR(512),
  TRK_NUMBER  INTEGER
);

CREATE UNIQUE INDEX IDX_ALB_DISC_ID ON ALBUM(ALB_DISC_ID);

CREATE UNIQUE INDEX IDX_ALB_TRK_DISC_ID ON TRACK(TRK_ALB_ID, TRK_NUMBER);

CREATE INDEX IDX_TRK_ALB_ID ON TRACK(TRK_ALB_ID);

CREATE INDEX IDX_TRK_NAME ON TRACK(TRK_NAME);

