import {NextResponse} from "next/server";

const backendUrl =
    process.env.BFF_BACKEND_URL ?? "http://localhost:8080";

export async function GET() {
    try {
        const response = await fetch(`${backendUrl}/actuator/health`, {
            cache: "no-store",
        });

        const data = await response.json();

        return NextResponse.json(data, {
            status: response.status,
        });
    } catch {
        return NextResponse.json(
            {status: "OFFLINE"},
            {status: 503},
        );
    }
}