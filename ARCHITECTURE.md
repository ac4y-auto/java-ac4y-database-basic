# Architektura - java-ac4y-database-basic

## Attekintes

Az `Ac4yDBAdapter` egy egyszeru JDBC Connection wrapper osztaly. Celja, hogy az adatbazis kapcsolatot egysegesen lehessen kezelni es atadni az ac4y keretrendszeren belul.

## Szerkezet

```
src/main/java/ac4y/base/database/
  Ac4yDBAdapter.java       - JDBC Connection wrapper adapter
src/test/java/ac4y/base/database/
  Ac4yDBAdapterTest.java   - Egyseg tesztek (Mockito)
```

## Osztalyok

### Ac4yDBAdapter

- Alapertelmezett konstruktor: ures adapter letrehozasa
- Parameterezett konstruktor: adapter letrehozasa meglevo Connection-nel
- `getConnection()`: a tarolt Connection lekerese
- `setConnection(Connection)`: Connection beallitasa vagy csereje

## Fuggetlensegek

- Nincs ac4y fuggoseg
- Teszt: JUnit 4, Mockito

## Eredet

Az `IJAc4yDatabaseModule/Basic` modulbol kinyerve.
