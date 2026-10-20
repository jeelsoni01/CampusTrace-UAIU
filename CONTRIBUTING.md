# Contributing to CampusTrace UAIU

Thank you for helping improve the project. Keep contributions focused, easy to review, and compatible with JDK 17 and the NetBeans Ant configuration.

## Development setup

1. Fork and clone the repository.
2. Install JDK 17 or newer.
3. Start a local MySQL/MariaDB server, preferably through XAMPP.
4. Run `./compile.ps1` on Windows or use **Clean and Build** in NetBeans.
5. Launch the application with `./run.ps1` and test both user and administrator flows.

## Pull requests

- Create a branch such as `feature/item-photo` or `fix/search-pagination`.
- Keep unrelated formatting or generated files out of the change.
- Explain what changed, why it changed, and how it was verified.
- Include screenshots for visible UI changes.
- Never commit real credentials or personal data.

## Useful contribution areas

- Password hashing and safer authentication
- Automated DAO and database integration tests
- Accessibility and keyboard navigation
- Item image attachments
- Improved search, reporting, and audit history
- Cross-platform launch scripts

By contributing, you confirm that you have the right to submit your work and understand the repository's current usage notice in `NOTICE.md`.
