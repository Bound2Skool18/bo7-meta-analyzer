# BO7 Meta Analyzer – Frontend

This is the React + Vite dashboard for **BO7 Meta Analyzer**, a full‑stack app that tracks your Black Ops 7 loadouts and match performance, then visualizes stats like K/D, win rate, and weapon effectiveness.

The frontend talks to the Spring Boot backend running at `http://localhost:8080` and provides a UI for:

- Creating and selecting loadouts
- Recording matches (kills, deaths, accuracy, result, etc.)
- Viewing aggregated analytics exposed by the backend

> The backend lives in `backend/meta-analyzer` in the repository root.

## Tech Stack

- React + Vite
- JavaScript (ESM, JSX)
- CSS modules / plain CSS

## Prerequisites

- Node.js and npm installed
- Backend API running (see the root project README for backend setup)

## Getting Started

From the repository root:

```bash
cd frontend/bo7-dashboard
npm install
npm run dev
```

By default Vite will start on `http://localhost:5173`.

Make sure the backend is running on `http://localhost:8080` so API calls from the dashboard succeed.

## Key Files

- `src/main.jsx` – React entry point, mounts the app
- `src/App.jsx` – Top‑level component / layout
- `src/components/MatchForm.jsx` – Form for creating and submitting match data to the backend
- `src/App.css`, `src/index.css` – Styling

## Development Scripts

Available npm scripts:

- `npm run dev` – Start the Vite dev server with HMR
- `npm run build` – Build for production
- `npm run preview` – Preview the production build locally

## Environment / API URL

If you need to point the frontend at a different backend URL, you can configure a Vite environment variable (e.g. `VITE_API_BASE_URL`) and use it inside your fetch/axios calls.

Example `.env.local` (not committed):

```bash
VITE_API_BASE_URL=http://localhost:8080
```

Then in code:

```js
const API_BASE = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';
```

## Project Root Docs

For details about the overall architecture (backend, database, environment variables, and full setup), see the **root `README.md`** in the repository.
