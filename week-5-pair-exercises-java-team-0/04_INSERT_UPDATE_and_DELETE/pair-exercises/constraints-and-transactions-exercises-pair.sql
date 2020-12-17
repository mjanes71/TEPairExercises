-- Write queries to return the following:
-- Make the following changes in the "world" database.

-- 1. Add Superman's hometown, Smallville, Kansas to the city table. The 
-- countrycode is 'USA', and population of 45001. (Yes, I looked it up on 
-- Wikipedia.)
<<<<<<< HEAD
SELECT * from city WHERE district = 'Kansas';
=======
INSERT INTO city (countrycode, population, name, district)
VALUES ('USA', 45001, 'Smallville', 'Kansas');
>>>>>>> 2eb9f8951798282deee7f2ce45779e94d963f55d

-- 2. Add Kryptonese to the countrylanguage table. Kryptonese is spoken by 0.0001
-- percentage of the 'USA' population.
INSERT INTO countrylanguage (countrycode, language, isofficial, percentage)
VALUES ('USA', 'Kryptonese', false, 0.0001);

SELECT * FROM countrylanguage WHERE countrycode = 'USA';

-- 3. After heated debate, "Kryptonese" was renamed to "Krypto-babble", change 
-- the appropriate record accordingly.
UPDATE countrylanguage
SET language = 'Krypto-babble'
WHERE language = 'Kryptonese';

-- 4. Set the US captial to Smallville, Kansas in the country table.
UPDATE country
SET capital = (SELECT id FROM city WHERE name = 'Smallville')
WHERE code = 'USA';

SELECT * FROM country WHERE code = 'USA';

-- 5. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)
DELETE
FROM city
WHERE name = 'Smallville';
-- Will not succeed because a foreign key constraint is violated. Smallville is also referenced as the capital of USA in the country table.

-- 6. Return the US captial to Washington.
UPDATE country 
SET capital = (SELECT id FROM city WHERE name = 'Washington')
WHERE code = 'USA';

SELECT *
FROM country
WHERE code ='USA';

-- 7. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)
DELETE
FROM city
WHERE name = 'Smallville';
-- Does succeed because the city is not referenced in any other table. 

-- 8. Reverse the "is the official language" setting for all languages where the
-- country's year of independence is within the range of 1800 and 1972 
-- (exclusive). 
-- (590 rows affected)
UPDATE countrylanguage
SET isofficial = NOT isofficial
WHERE countrylanguage.countrycode IN (SELECT code FROM country WHERE indepyear BETWEEN 1800 AND 1972);

-- 9. Convert population so it is expressed in 1,000s for all cities. (Round to
-- the nearest integer value greater than 0.)
-- (4079 rows affected)
UPDATE city
SET population = ceil(population::numeric/1000);

-- 10. Assuming a country's surfacearea is expressed in square miles, convert it to 
-- square meters for all countries where French is spoken by more than 20% of the 
-- population.
-- (7 rows affected)
UPDATE country
SET surfacearea = 2589988.11 * surfacearea
WHERE code IN (SELECT countrycode FROM countrylanguage WHERE language = 'French' AND percentage > 20.0);
