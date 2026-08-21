// Функция для запроса к бэкенду
async function getBackendHealth() {
    try {
        // Внутри Docker-сети фронтенд может стучаться по имени сервиса бэкенда,
        // но для браузера лучше использовать localhost:8080 (или задать через переменные окружения)
        const res = await fetch("http://logistic-app:8080/actuator/health", {
            cache: "no-store", // чтобы данные не кешировались и проверялись при каждом обновлении
        });

        if (!res.ok) {
            return {status: "DOWN (Ошибка HTTP)"};
        }

        const data = await res.json();
        return data; // Обычно Spring возвращает { "status": "UP" }
    } catch (error) {
        return {status: "OFFLINE (Нет связи с бэком)"};
    }
}

export default async function Home() {
    const healthData = await getBackendHealth();
    const isUp = healthData.status === "UP";

    return (
        <main className="min-h-screen bg-slate-900 text-white p-8 font-sans">
            <div className="max-w-4xl mx-auto">
                <h1 className="text-3xl font-extrabold mb-2">Logistic platform</h1>
                <p className="text-slate-400 mb-8">Система автоматизации магазина</p>

                {/* Плашка статуса бэкенда */}
                <div
                    className="p-6 bg-slate-800 border border-slate-700 rounded-xl shadow-lg flex items-center justify-between">
                    <div>
                        <h2 className="text-lg font-semibold text-slate-200">Статус бэкенда (Spring Actuator)</h2>
                        <p className="text-sm text-slate-400">Подключение к Java-серверу</p>
                    </div>
                    <div className="flex items-center gap-3">
                        <span
                            className={`h-3 w-3 rounded-full ${isUp ? "bg-emerald-500 animate-pulse" : "bg-red-500"}`}/>
                        <span
                            className={`font-bold px-3 py-1 rounded-lg text-sm ${isUp ? "bg-emerald-500/10 text-emerald-400" : "bg-red-500/10 text-red-400"}`}>
              {healthData.status}
            </span>
                    </div>
                </div>
            </div>
        </main>
    );
}