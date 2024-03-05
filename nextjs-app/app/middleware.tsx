// import type { NextRequest } from "next/server";
// import { NextResponse } from "next/server";
//
// export async function middleware(req: NextRequest) {
//     if (req.nextUrl.pathname.startsWith("/*")) {
//
//         const res = NextResponse.next()
//
//         res.headers.append('Access-Control-Allow-Credentials', "true")
//         res.headers.append('Access-Control-Allow-Origin', '*')
//         res.headers.append('Access-Control-Allow-Methods', 'GET,DELETE,PATCH,POST,PUT,OPTIONS')
//         res.headers.append(
//             'Access-Control-Allow-Headers',
//             'X-CSRF-Token, X-Requested-With, Authorisation, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version'
//         )
//         return res
//     }
// }
//
// export const config = {
//     matcher: "/((?!_next/static|_next/image|favicon.ico|login|setup).*)",
// };
