import { useState } from "react";
import axios from "axios";

export default function MatchForm({ onMatchSubmit }) {
  const [formData, setFormData] = useState({
    loadout_id: "",
    kills: "",
    deaths: "",
    accuracy: "",
    result: "",
    duration: "",
  });

  const [status, setStatus] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setStatus("Submitting...");
    try {
      const res = await axios.post("http://localhost:8080/api/matches", {
        ...formData,
        loadout_id: Number(formData.loadout_id),
        kills: Number(formData.kills),
        deaths: Number(formData.deaths),
        accuracy: Number(formData.accuracy),
        duration: Number(formData.duration),
      });
      setStatus("✅ Match submitted successfully!");
      setFormData({
        loadout_id: "",
        kills: "",
        deaths: "",
        accuracy: "",
        result: "",
        duration: "",
      });
      if (onMatchSubmit) onMatchSubmit(res.data); // refresh dashboard
    } catch (err) {
      console.error(err);
      setStatus("❌ Error submitting match. Check backend logs.");
    }
  };

  return (
    <div style={{ marginTop: "2rem", background: "#2e2e2e", padding: "1rem", borderRadius: "8px" }}>
      <h2>📝 Submit New Match</h2>
      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "0.5rem" }}>
        <input name="loadout_id" placeholder="Loadout ID" value={formData.loadout_id} onChange={handleChange} required />
        <input name="kills" placeholder="Kills" value={formData.kills} onChange={handleChange} required />
        <input name="deaths" placeholder="Deaths" value={formData.deaths} onChange={handleChange} required />
        <input name="accuracy" placeholder="Accuracy (0.00 to 1.00)" value={formData.accuracy} onChange={handleChange} required />
        <input name="duration" placeholder="Duration (seconds)" value={formData.duration} onChange={handleChange} required />
        <select name="result" value={formData.result} onChange={handleChange} required>
          <option value="">Select Result</option>
          <option value="Win">Win</option>
          <option value="Loss">Loss</option>
        </select>

        <button
          type="submit"
          style={{
            backgroundColor: "#4caf50",
            border: "none",
            color: "white",
            padding: "0.6rem",
            cursor: "pointer",
            borderRadius: "4px",
          }}
        >
          Submit Match
        </button>
      </form>
      {status && <p>{status}</p>}
    </div>
  );
}
