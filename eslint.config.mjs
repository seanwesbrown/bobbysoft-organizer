// @ts-check
import { globalIgnores } from "eslint/config";

import eslint from '@eslint/js';
import tseslint from 'typescript-eslint';

export default tseslint.config(
  eslint.configs.recommended,
  tseslint.configs.recommended,
  globalIgnores([
    "**/target/*", 
    "**/generated/*",
    "**/types.d.ts",
    "**/vite.config.ts",
    "**/vite.generated.ts",
  ])
);