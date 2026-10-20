USE `LostFoundDB`;

UPDATE `users`
SET `name`='UAU Lost & Found Administrator',
    `location`='Universal AI University, Karjat',
    `phone`='9876500001'
WHERE `username`='admin' AND `name` IN ('Administrator', 'UAU Lost & Found Administrator');

UPDATE `users`
SET `name`='Aarav Sharma',
    `location`='Universal AI University, Karjat',
    `phone`='9876500002'
WHERE `username`='user' AND `name` IN ('Standard User', 'Aarav Sharma');

-- The application upgrades its bundled 29-row sample dataset to the current
-- Universal AI University Indian-campus profile on the next launch. Records
-- added by users are not replaced by that migration.
