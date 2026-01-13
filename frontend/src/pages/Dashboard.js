import React, { useEffect, useState } from "react";
import api from "../api/axios";
import Sidebar from "../components/sidebar";
import Topbar from "../components/Topbar";
import StatCard from "../components/StatCard";

function Dashboard() {

  const [data, setData] = useState(null);

  useEffect(() => {
    api.get("/dashboard")
      .then(res => setData(res.data))
      .catch(err => console.log(err));
  }, []);

  if (!data) return <p>Loading...</p>;

  return (
    <div style={{ display: "flex" }}>
      <Sidebar />

      <div style={{ flex: 1, background: "#f5f7fb" }}>
        <Topbar />

        <div style={styles.cards}>
          <StatCard title="Total Spent" value={data.totalSpent} />
          <StatCard title="You Owe" value={data.youOwe} />
          <StatCard title="You Are Owed" value={data.youAreOwed} />
        </div>

        <div style={styles.section}>
          <h3>Groups</h3>
          {data.groups.map(g => (
            <p key={g.id}>{g.groupName}</p>
          ))}
        </div>

        <div style={styles.section}>
          <h3>Recent Expenses</h3>
          {data.recentExpenses.map((e, i) => (
            <p key={i}>{e.title} - ₹{e.amount}</p>
          ))}
        </div>
      </div>
    </div>
  );
}

const styles = {
  cards: {
    display: "flex",
    justifyContent: "space-between",
    padding: "20px"
  },
  section: {
    background: "white",
    margin: "20px",
    padding: "20px",
    borderRadius: "10px"
  }
};

export default Dashboard;
