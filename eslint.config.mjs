// @ts-check
import { globalIgnores } from 'eslint/config';

import eslint from '@eslint/js';
import tseslint from 'typescript-eslint';

import eslintPluginPrettierRecommended from 'eslint-plugin-prettier/recommended';

export default tseslint.config(
  eslint.configs.recommended,
  tseslint.configs.recommended,
  eslintPluginPrettierRecommended,
  globalIgnores(['**/target/*', '**/generated/*', '**/types.d.ts', '**/vite.config.ts', '**/vite.generated.ts']),
);
