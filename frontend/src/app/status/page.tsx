"use client";

import { useEffect, useState } from "react";

type HealthData = {
  status: string;
};

export default function StatusPage() {
  const [healthData, setHealthData] = useState<HealthData>({
    status: "LOADING",
  });

  useEffect(() => {
    fetch("/api/health", { cache: "no-store" })
      .then((response) => response.json())
      .then(setHealthData)
      .catch(() => setHealthData({ status: "OFFLINE" }));
  }, []);

  const isUp = healthData.status === "UP";

  return (
    <main className="min-h-screen bg-slate-900 text-white p-8 font-sans">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-3xl font-extrabold mb-2">Logistic platform</h1>
        <p className="text-slate-400 mb-8">Store automation platform</p>

        <div className="p-6 bg-slate-800 border border-slate-700 rounded-xl shadow-lg flex items-center justify-between">
          <div>
            <h2 className="text-lg font-semibold text-slate-200">
              Backend status (Spring Actuator)
            </h2>
            <p className="text-sm text-slate-400">
              Connection through the Next.js BFF
            </p>
          </div>
          <div className="flex items-center gap-3">
            <span
              className={`h-3 w-3 rounded-full ${
                isUp ? "bg-emerald-500 animate-pulse" : "bg-red-500"
              }`}
            />
            <span
              className={`font-bold px-3 py-1 rounded-lg text-sm ${
                isUp
                  ? "bg-emerald-500/10 text-emerald-400"
                  : "bg-red-500/10 text-red-400"
              }`}
            >
              {healthData.status}
            </span>
          </div>
        </div>
      </div>
    </main>
  );
}
