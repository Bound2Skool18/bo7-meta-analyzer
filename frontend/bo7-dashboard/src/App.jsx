import { useEffect, useState } from "react";
import axios from "axios";
import { BarChart, Bar, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from "recharts";
import MatchForm from "./components/MatchForm";


function App() {
  const [summary, setSummary] = useState({});
  const [topWeapons, setTopWeapons] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchData = async () => {
    try {
      setLoading(true);
      setError("");
      const [summaryRes, weaponsRes] = await Promise.all([
        axios.get("http://localhost:8080/api/analytics/summary"),
        axios.get("http://localhost:8080/api/analytics/top-weapons"),
      ]);
      setSummary(summaryRes.data);
      setTopWeapons(weaponsRes.data);
    } catch (err) {
      setError("Failed to load data. Please check if backend is running.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (loading)
    return (
      <div style={{ color: "white", fontFamily: "Arial", textAlign: "center", marginTop: "3rem" }}>
        <h2>Loading stats...</h2>
      </div>
    );

  if (error)
    return (
      <div style={{ color: "red", fontFamily: "Arial", textAlign: "center", marginTop: "3rem" }}>
        <h2>{error}</h2>
        <button onClick={fetchData}>Retry</button>
      </div>
    );

  return (
    <div style={{ fontFamily: "Arial, sans-serif", padding: "2rem", color: "white", backgroundColor: "#1e1e1e" }}>
      <h1>🎯 BO7 Meta Analyzer Dashboard</h1>

      <section style={{ marginBottom: "2rem" }}>
        <h2>📊 Summary Statistics</h2>
        <ul>
          <li><strong>Average Kills:</strong> {summary.averageKills}</li>
          <li><strong>Average Deaths:</strong> {summary.averageDeaths}</li>
          <li><strong>K/D Ratio:</strong> {summary.KD_Ratio}</li>
          <li><strong>Average Accuracy:</strong> {summary.averageAccuracy}</li>
          <li><strong>Win Rate:</strong> {summary.winRate}%</li>
        </ul>
        <button
          onClick={fetchData}
          style={{
            marginTop: "1rem",
            padding: "0.5rem 1rem",
            backgroundColor: "#4caf50",
            border: "none",
            color: "white",
            cursor: "pointer",
            borderRadius: "5px",
          }}
        >
          🔄 Refresh Data
        </button>
      </section>

      <section>
        <h2>🔫 Top Weapons by K/D Ratio</h2>
        {topWeapons.length > 0 ? (
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={topWeapons}>
              <XAxis dataKey="weapon" />
              <YAxis />
              <Tooltip />
              <Legend />
              <Bar dataKey="KD_Ratio" fill="#82ca9d" />
            </BarChart>
          </ResponsiveContainer>
        ) : (
          <p>No weapon data yet. Play more matches!</p>
        )}
      </section>
      <MatchForm onMatchSubmit={fetchData} />
    </div>
  );
}

export default App;
