import Link from "next/link";

export default function Home() {
    return (
        <main className="min-h-screen bg-slate-900 text-white flex flex-col justify-between p-8 font-sans">
            {/* Шапка */}
            <header
                className="max-w-5xl mx-auto w-full flex justify-between items-center py-4 border-b border-slate-800">
                <h2 className="text-xl font-bold tracking-tight text-emerald-400">Logistic platform</h2>
                <span className="text-xs px-2.5 py-1 rounded-full bg-slate-800 text-slate-400 border border-slate-700">
          Portfolio Project v1.0
        </span>
            </header>

            {/* Основной контент (Приветствие) */}
            <div className="max-w-3xl mx-auto text-center my-auto py-12">
                <h1 className="text-4xl sm:text-5xl font-extrabold tracking-tight mb-6">
                    Система автоматизации <span className="text-emerald-400">розничного магазина</span>
                </h1>
                <p className="text-lg text-slate-400 mb-8 leading-relaxed">
                    Современный программный комплекс для управления складом, товарами и кассовыми операциями.
                    Проект разработан на связке Spring Boot и Next.js.
                </p>

                {/* Кнопки навигации */}
                <div className="flex flex-col sm:flex-row justify-center gap-4">
                    <Link
                        href="/status"
                        className="px-6 py-3 rounded-xl bg-emerald-600 hover:bg-emerald-500 font-semibold transition shadow-lg shadow-emerald-900/30 text-white"
                    >
                        Проверить статус бэкенда 🔌
                    </Link>
                    <a
                        href="https://github.com"
                        target="_blank"
                        rel="noopener noreferrer"
                        className="px-6 py-3 rounded-xl bg-slate-800 hover:bg-slate-700 font-semibold transition border border-slate-700 text-slate-300"
                    >
                        Исходный код (GitHub)
                    </a>
                </div>
            </div>

            {/* Подвал */}
            <footer
                className="max-w-5xl mx-auto w-full text-center text-xs text-slate-500 py-4 border-t border-slate-800">
                Java Developer Portfolio • Spring Boot + Next.js + PostgreSQL + Docker
            </footer>
        </main>
    );
}